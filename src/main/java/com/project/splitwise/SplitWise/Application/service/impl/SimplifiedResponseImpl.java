package com.project.splitwise.SplitWise.Application.service.impl;

import com.project.splitwise.SplitWise.Application.Algo.SplitwiseSimplification;
import com.project.splitwise.SplitWise.Application.dao.entity.Group;
import com.project.splitwise.SplitWise.Application.dao.entity.SimplifiedResponse;
import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;
import com.project.splitwise.SplitWise.Application.dao.entity.User;
import com.project.splitwise.SplitWise.Application.dao.repository.GroupRepository;
import com.project.splitwise.SplitWise.Application.dao.repository.SimplifiedResponseRepository;
import com.project.splitwise.SplitWise.Application.service.SimplifiedResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SimplifiedResponseImpl implements SimplifiedResponseService {
    @Autowired
    private final SimplifiedResponseRepository simplifiedResponseRepository;

    @Autowired
    private final GroupRepository groupRepository;
    @Override
    public SimplifiedResponse solve(Transaction transaction) {
        Long grpId = transaction.getGroup().getId();
        Optional<Group> g = groupRepository.findById(grpId);
        if(g.isEmpty())
        {
            return null; //write logic here

        }
        else
        {
            Optional<SimplifiedResponse> simplifiedResponse = simplifiedResponseRepository.findByGroup(g.get());

            if(simplifiedResponse.isEmpty())
            {
                //write logic here
            }
            else
            {
                simplifiedResponse.get().setMap(null);
                List<Transaction> transactions = g.get().getTransactions();
                Map<User, Double> balances = new HashMap<>();
                for (Transaction t : transactions) {
                    balances.put( t.getFromUser() , balances.getOrDefault(t.getToUser() , 0.0) - t.getAmount()  );
                    balances.put(t.getToUser() , balances.getOrDefault(t.getFromUser(), 0.0) + t.getAmount() );
                }

                List<Map.Entry<User, Double>> oweList = new ArrayList<>();
                List<Map.Entry<User, Double>> owedList = new ArrayList<>();

                for (Map.Entry<User, Double> entry : balances.entrySet()) {
                    if (entry.getValue() < 0.0) {
                        oweList.add(entry);
                    } else if (entry.getValue() > 0) {
                        owedList.add(entry);
                    }
                }
                int i = 0, j = 0;
                Map<User , Map<User, Double>> hm = new HashMap<>();
                while (i < oweList.size() && j < owedList.size()) {
                    Map.Entry<User, Double> owe = oweList.get(i);
                    Map.Entry<User, Double> owed = owedList.get(j);
                    double settle = Math.min(-owe.getValue(), owed.getValue());
                    System.out.println(owe.getKey().getName() + " pays " + settle + " to " + owed.getKey().getName());
                    Map<User, Double> innerMap = new HashMap<>();
                    innerMap.put(owed.getKey(), settle);
                    hm.put(owe.getKey() , innerMap);

                }
                simplifiedResponse.get().setMap(hm);
                return simplifiedResponse.get();


                }
        }
    }
}

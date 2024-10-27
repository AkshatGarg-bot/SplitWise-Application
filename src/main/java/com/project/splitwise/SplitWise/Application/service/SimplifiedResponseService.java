package com.project.splitwise.SplitWise.Application.service;

import com.project.splitwise.SplitWise.Application.dao.entity.SimplifiedResponse;
import com.project.splitwise.SplitWise.Application.dao.entity.Transaction;

public interface SimplifiedResponseService {
    SimplifiedResponse solve(Transaction transaction);
}

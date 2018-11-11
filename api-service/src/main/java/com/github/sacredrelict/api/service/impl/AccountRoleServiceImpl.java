package com.github.sacredrelict.api.service.impl;

import com.github.sacredrelict.api.client.DataServiceClient;
import com.github.sacredrelict.api.dto.AccountRole;
import com.github.sacredrelict.api.service.AccountRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer implementation for AccountRole.
 */
@Service
public class AccountRoleServiceImpl implements AccountRoleService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountRoleServiceImpl.class);

    @Autowired
    private DataServiceClient dataServiceClient;

    /**
     * Search Account Roles by Account id.
     * @param accountId - Account id.
     * @return list of Account Roles.
     */
    @Override
    public List<AccountRole> getRolesByAccountId(Long accountId) {
        return dataServiceClient.findByAccountId(accountId);
    }

}

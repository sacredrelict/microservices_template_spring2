package com.github.sacredrelict.api.validation;

import com.github.sacredrelict.api.client.DataServiceClient;
import com.github.sacredrelict.api.common.component.message.Messages;
import com.github.sacredrelict.api.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Map;

/**
 * Additional validation for the Account.
 */
@Component
public class AccountValidation extends BaseValidation {

    @Autowired
    private BaseValidation baseValidation;

    @Autowired
    private DataServiceClient dataServiceClient;

    @Autowired
    private Messages messages;

    /**
     * Validate Account for create operation.
     * @param bindingResult - Default validate result depends on entity annotations.
     * @param account - Account object that will validate here.
     * @return map of errors if they found.
     */
    public Map<String, String> validateAccountForCreate(BindingResult bindingResult, Account account) {
        Map<String, String> fieldErrorsMap = baseValidation.validateBase(bindingResult);

        if (StringUtils.isEmpty(account.getLogin())) {
            fieldErrorsMap.put("login", messages.get("account.login.is.empty"));
        } else {
            Account accountTemp = dataServiceClient.findAccountByLogin(account.getLogin());
            if (accountTemp != null) {
                fieldErrorsMap.put("account", String.format(messages.get("account.with.login.exists"), account.getLogin()));
            }
        }

        if (StringUtils.isEmpty(account.getEmail())) {
            fieldErrorsMap.put("email", messages.get("account.email.is.empty"));
        }

        if (StringUtils.isEmpty(account.getPassword())) {
            fieldErrorsMap.put("password", messages.get("account.password.is.empty"));
        }

        if (account.getActive() == null) {
            fieldErrorsMap.put("active", messages.get("account.active.is.null"));
        }

        return fieldErrorsMap;
    }

    /**
     * Validate Account for edit operation.
     * @param bindingResult - Default validate result depends on entity annotations.
     * @param account - Account object that will validate here.
     * @return map of errors if they found.
     */
    public Map<String, String> validateAccountForUpdate(BindingResult bindingResult, Account account) {
        Map<String, String> fieldErrorsMap = baseValidation.validateBase(bindingResult);

        if (account.getId() == null) {
            fieldErrorsMap.put("id", messages.get("account.id.is.null"));
        } else {
            Account accountCurrent = dataServiceClient.findAccountById(account.getId());
            if (accountCurrent == null) {
                fieldErrorsMap.put("account", messages.get("account.not.found"));
            } else {
                if (!StringUtils.isEmpty(account.getLogin())) {
                    accountCurrent.setLogin(account.getLogin());
                }
            }
        }

        return fieldErrorsMap;
    }

}

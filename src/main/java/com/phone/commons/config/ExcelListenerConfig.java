package com.phone.commons.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.phone.modules.domain.User;
import com.phone.modules.service.UserService;

import java.util.List;

/**
 * @description
 */
public class ExcelListenerConfig extends AnalysisEventListener<User> {
    private  final UserService userService;

    private final List<User> users = ListUtils.newArrayList();

    public ExcelListenerConfig(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void invoke(User user, AnalysisContext context) {
        users.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveUsers(users);
    }

    private void saveUsers(List<User> users) {
        userService.saveBatch(users);
        users.clear();
    }
}

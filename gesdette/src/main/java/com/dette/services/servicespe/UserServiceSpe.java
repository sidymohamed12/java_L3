package com.dette.services.servicespe;

import com.dette.core.Service;
import com.dette.entities.User;

public interface UserServiceSpe extends Service<User> {
    void modifier(User user);
}

package com.shoppingWebsite.service;

import com.shoppingWebsite.security.model.AuthenticationRequest;
import com.shoppingWebsite.security.model.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}

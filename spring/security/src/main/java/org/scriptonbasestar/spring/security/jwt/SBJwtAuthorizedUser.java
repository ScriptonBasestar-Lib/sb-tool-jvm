package org.scriptonbasestar.spring.security.jwt;

import org.scriptonbasestar.spring.security.auth.SBAuthorizedUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author chaeeung.e
 * @since 2017-09-25
 */
public class SBJwtAuthorizedUser extends SBAuthorizedUser {
	private SBJwtAuthorizedUser(Long userId, String nickname, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(userId, nickname, username, password, authorities);
	}
}

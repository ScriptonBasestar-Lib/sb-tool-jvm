package org.scriptonbasestar.spring.security.filter

import org.scriptonbasestar.tool.core.check.Check
import org.springframework.http.HttpHeaders
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author archmagece
 * @since 2017-10-11
 */
class GuardByKeySecretFilter : OncePerRequestFilter() {

	lateinit var authKey: String
	var authKeyLength: Int = 0
	lateinit var authSecret: String

	override fun initFilterBean(){
//		this.authKey = filterConfig.getInitParameter("config.server.auth-key").trim()+" "
//		this.authKeyLength = authKey.length
//		this.authCode = filterConfig.getInitParameter("config.server.auth-code")

		Check.notNullOrEmptyString(authKey, "authKey must have value")
		Check.notNullOrEmptyString(authSecret, "authSecret must have value")
		this.authKeyLength = authKey.length
	}

	override fun doFilterInternal(request: HttpServletRequest?, response: HttpServletResponse?, filterChain: FilterChain?) {
		var header = request!!.getHeader(HttpHeaders.AUTHORIZATION)

		//헤더가 있고 키가 맞으면
		if (header != null && header.startsWith(authKey)) {
			var serverAuthCodeParam = header.substring(authKeyLength)

			if(serverAuthCodeParam.trim().contentEquals(authSecret)){
				//코드값이 맞으면 다음으로
				filterChain!!.doFilter(request, response)
				return
			}
		}
		//헤더가 없거나 코드값이 틀리면 접근금지
		throw RuntimeException("wrong code! api auth failed.")
	}
}

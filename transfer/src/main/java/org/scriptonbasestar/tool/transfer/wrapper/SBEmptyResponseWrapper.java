package org.scriptonbasestar.tool.transfer.wrapper;

import lombok.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author archmagece
 * @since 2017-08-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SBEmptyResponseWrapper {

	public static SBEmptyResponseWrapper create() {
		return new SBEmptyResponseWrapper();
	}

	@Setter(AccessLevel.PROTECTED)
	private long leadTime;

	public SBEmptyResponseWrapper leadTime(long leadTime) {
		this.leadTime = leadTime;
		return this;
	}

	public SBEmptyResponseWrapper leadTimeCalc(long start, long end) {
		this.leadTime = end - start;
		return this;
	}

	public SBEmptyResponseWrapper leadTimeCalc(long start) {
		this.leadTime = System.currentTimeMillis() - start;
		return this;
	}

	private boolean success = true;

	public SBEmptyResponseWrapper success() {
		this.success = true;
		return this;
	}

	public SBEmptyResponseWrapper fail() {
		success = false;
		return this;
	}

	private String lang;

	public SBEmptyResponseWrapper lang(String lang) {
		this.lang = lang;
		return this;
	}

	/**
	 * 1000: success
	 * error
	 * 3000: validation
	 * 4000: input
	 * 5000: server
	 * 6000: database
	 * 7000: network
	 * 9000: unknown
	 */
	private String code;

	public SBEmptyResponseWrapper code(String code) {
		this.code = code;
		return this;
	}

	private String message;

	public SBEmptyResponseWrapper message(String message) {
		this.message = message;
		return this;
	}

	protected Set<Map<String, String>> validationErrorSet = new HashSet<>();

}

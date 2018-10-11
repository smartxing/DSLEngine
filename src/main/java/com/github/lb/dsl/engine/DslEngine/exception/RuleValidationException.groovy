package com.github.lb.dsl.engine.DslEngine.exception

import groovy.transform.Immutable;

@Immutable
class RuleValidationException extends RuntimeException {
	String ruleName;
	String scriptName;
	List<String> errors;
}

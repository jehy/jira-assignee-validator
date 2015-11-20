package com.sma.plugins.jira.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.Validator;
import com.opensymphony.workflow.InvalidInputException;

import java.util.Map;

public class assignee_validator implements Validator {
	private static final Logger log = LoggerFactory
			.getLogger(assignee_validator.class);

	// public static final String FIELD_WORD="word";

	public void validate(Map transientVars, Map args, PropertySet ps)
			throws InvalidInputException {
		// String word = (String) transientVars.get(FIELD_WORD);

		Issue issue = (Issue) transientVars.get("issue");

		// if(null == issue.getDescription() ||
		// "".equals(issue.getDescription()) ||
		// !issue.getDescription().contains(word)) {
		// throw new InvalidInputException("Issue must contain the word '" +
		// word + "' in the description");
		// }

		IssueManager issueManager = ComponentAccessor.getIssueManager();
		String assignee_old = issueManager.getIssueObject(issue.getId())
				.getAssigneeId();
		String assignee_new = issue.getAssigneeId();
		if (assignee_new.equalsIgnoreCase(assignee_old))
			throw new InvalidInputException(
					"Исполнитель задачи не может остаться неизменным!");
	}
}

package vn.edu.vnuk.fashin.helper;

public class DaoHelpers {
	public static String addConditionForQuery(String query, String name, String value) {
		if (query.toLowerCase().contains("where")) {
			query += String.format(" and %s=%s", name, value);
		} else {
			query += String.format(" where %s=%s", name, value);
		}
		return query;
	}
}

package my.learning.annotations;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Repository<T> {

	public void save(T t) {

		var clazz = t.getClass();
		var classAnnotations = clazz.getDeclaredAnnotationsByType(Entity.class);
		var tableName = clazz.getSimpleName().toLowerCase();
		
		if(classAnnotations.length > 0 && !classAnnotations[0].value().isBlank()) {
			tableName = classAnnotations[0].value();
		}
		
		var fields = clazz.getDeclaredFields();
		ArrayList<String> fieldList = new ArrayList<>();
		
		for(var field : fields) {
			var annotations = field.getAnnotationsByType(Field.class);
			if(annotations.length == 0) {
				continue;
			}
			var annotation = annotations[0];
			var fieldName = annotation.columnName();
			var isKey = annotation.isKey();
			
			if(fieldName.isEmpty()) {
				fieldName = field.getName();
			}
			
			if(!isKey) {
				fieldList.add(fieldName);
			}
		}
		
		String sqlFields = fieldList.stream().collect(Collectors.joining(", "));
		String sqlPlaceholders  = fieldList.stream().map(s -> "?").collect(Collectors.joining(", "));
		
		String sql = String.format("insert into %s (%s) values (%s)", tableName, sqlFields, sqlPlaceholders);
		System.out.println(sql);

	}
	
	/* var fieldList = Arrays
			.stream(clazz.getDeclaredFields())
			.filter(f -> f.getDeclaredAnnotationsByType(Field.class).length > 0)
			.collect(Collectors.toList());

	System.out.println();
	System.out.println(fieldList); */
}

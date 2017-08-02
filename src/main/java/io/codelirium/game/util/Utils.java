package io.codelirium.game.util;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class Utils {

	private Utils() { }


	public static <T> List<T> getObjectInstancesFromPackageAnnotatedWith(final String packageName, final Class<? extends Annotation> annotationClazz) {

		final Set<Class<?>> modelClazzes = new Reflections(packageName.replace("package ", "")).getTypesAnnotatedWith(annotationClazz);

		final List<T> instances = new ArrayList<T>(modelClazzes.size());

		try {

			for (Class<?> modelClazz : modelClazzes) {
				instances.add((T) modelClazz.newInstance());
			}

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return Collections.unmodifiableList(instances);
	}
}

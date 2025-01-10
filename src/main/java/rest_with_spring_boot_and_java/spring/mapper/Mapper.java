package rest_with_spring_boot_and_java.spring.mapper;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static <S, T> T parseObject(S source, Class<T> targetClass) {

        if (source == null) {
            return null;
        }

        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Error while copying proprieties", e);
        }

    }

    public static <S, T> List<T> parseListObjects(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return List.of();
        }

       return sourceList.stream()
                .map(source -> parseObject(source, targetClass))
                .collect(Collectors.toList());
    }



}

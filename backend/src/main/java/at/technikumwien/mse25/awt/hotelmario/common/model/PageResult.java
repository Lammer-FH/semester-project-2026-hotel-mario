package at.technikumwien.mse25.awt.hotelmario.common.model;

import java.util.List;

public record PageResult<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages) {}

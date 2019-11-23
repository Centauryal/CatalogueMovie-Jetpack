package com.centaury.cataloguemovie.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.centaury.cataloguemovie.data.remote.StatusResponse.EMPTY;
import static com.centaury.cataloguemovie.data.remote.StatusResponse.ERROR;
import static com.centaury.cataloguemovie.data.remote.StatusResponse.SUCCESS;

/**
 * Created by Centaury on 11/20/2019.
 */
public class ApiResponse<T> {

    @NonNull
    public final StatusResponse status;

    @Nullable
    public final String message;

    @Nullable
    public T body;

    public ApiResponse(@NonNull StatusResponse status, @Nullable T body, @Nullable String message) {
        this.status = status;
        this.body = body;
        this.message = message;
    }

    public ApiResponse(@NonNull StatusResponse status, @Nullable String message) {
        this.status = status;
        this.message = message;
    }

    public static <T> ApiResponse<T> success(@Nullable T body) {
        return new ApiResponse<>(SUCCESS, body, null);
    }

    public static <T> ApiResponse<T> empty(String msg, @Nullable T body) {
        return new ApiResponse<>(EMPTY, body, msg);
    }

    public static <T> ApiResponse<T> error(String msg) {
        return new ApiResponse<>(ERROR, msg);
    }
}

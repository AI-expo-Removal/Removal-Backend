package com.example.removalbackend.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    UNMATCHED_PASSWORD(400, "Unmatched Password"),
    INCORRECT_PASSWORD(400, "Incorrect Password"),
    INCORRECT_USER(400, "Incorrect User"),
    VIDEO_NOT_FOUND(400, "Video Not Found"),
    BAD_FILE_EXTENSION(400, "Bad File Extension"),
    NOT_ALLOW_SELF_APPLICATION(400, "Not Allow Self Application"),
    FAILED_TO_CREATE_VIDEO(400,"Failed to create video"),

    TOKEN_INVALID(401, "Token Invalid"),
    TOKEN_EXPIRED(401, "Token Expired"),

    USER_NOT_FOUND(404, "User Not Found"),

    ALREADY_ACCOUNT_ID(409, "Already Account Id"),
    ALREADY_EXIST_LIKE(409, "Already Exist Like"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}
package br.com.leucotron.livre.util;

public enum Comparison {
    // equal
    EQ,
    // greaterThan
    GT,
    // lowerThan
    LT,
    // not equal
    NE,
    // is null
    ISNULL,
    // multiple values in a WHERE clause
    IN,
    // contains
    LIKE,
    // Finds any values that start with "value"
    LIKE_S,
    // Finds any values that end with "value"
    LIKE_E,
}

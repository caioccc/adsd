package br.com.leucotron.livre.util;

import org.json.JSONObject;

public class JsonUtil {

    public static final String NAME = "name";
    public static final String STATUS = "status";
    public static final String TAGS = "tags";
    public static final String DESCRIPTION = "description";
    public static final String USER = "user";
    public static final String ID_ORGANIZATION = "idOrganization";
    public static final String DATE_UPDATE = "dateUpdate";
    public static final String PROJECT_NAME = "Project X";
    public static final String PROJECT_DESCRIPTION = "New project test";
    public static final String PROJECT_TAGS = ",new,";
    public static final boolean PROJECT_STATUS = true;
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_SIZE = "pageSize";
    public static final String FILTERS = "filters";
    public static final String COLUMN = "column";
    public static final String SORT = "sort";
    public static final String FILTER = "filter";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String ITEMS = "items";
    public static final String VARIABLE_NAME = "getDigits";
    public static final String VARIABLE_TYPE = "GLOBAL";
    public static final String TYPE = "type";
    public static final String ID_PROJECT = "idProject";
    public static final String ID_USER = "idUser";
    public static final String ASSOCIATED = "associated";


    public static JSONObject createJsonObject() {
        return new JSONObject();
    }
}

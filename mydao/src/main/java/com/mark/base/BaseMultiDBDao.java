package com.mark.base;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mark.domain.Student;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulei on 2018/5/14.
 */
public class BaseMultiDBDao {
    protected List<SqlMapClientTemplate> sqlMapClientTemplateList = new ArrayList<>();
    private List<SqlMapClient> sqlMapClientList = new ArrayList<>();

    public void setSqlMapClientList(List<SqlMapClient> sqlMapClientList) {
        this.sqlMapClientList = sqlMapClientList;
        for (SqlMapClient sqlMapClient : sqlMapClientList) {
            sqlMapClientTemplateList.add(new SqlMapClientTemplate(sqlMapClient));
        }
    }

    public SqlMapClientTemplate getExecutor(String key){
        Student student = new Student();
        int dbIndex = student.getDbBase().getDBIndex(key);
        return this.sqlMapClientTemplateList.get(dbIndex);
    }

}

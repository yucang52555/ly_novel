package com.lyqiaofu.novel.mapper;

import static com.lyqiaofu.novel.mapper.CrawlSingleTaskDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.lyqiaofu.novel.entity.CrawlSingleTask;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface CrawlSingleTaskMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, sourceId, sourceBookId, taskStatus, excCount, createTime);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<CrawlSingleTask> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<CrawlSingleTask> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CrawlSingleTaskResult")
    Optional<CrawlSingleTask> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CrawlSingleTaskResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="source_id", property="sourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="source_book_id", property="sourceBookId", jdbcType=JdbcType.VARCHAR),
        @Result(column="task_status", property="taskStatus", jdbcType=JdbcType.TINYINT),
        @Result(column="exc_count", property="excCount", jdbcType=JdbcType.TINYINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<CrawlSingleTask> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, crawlSingleTask, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, crawlSingleTask, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(CrawlSingleTask record) {
        return MyBatis3Utils.insert(this::insert, record, crawlSingleTask, c ->
            c.map(id).toProperty("id")
            .map(sourceId).toProperty("sourceId")
            .map(sourceBookId).toProperty("sourceBookId")
            .map(taskStatus).toProperty("taskStatus")
            .map(excCount).toProperty("excCount")
            .map(createTime).toProperty("createTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<CrawlSingleTask> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, crawlSingleTask, c ->
            c.map(id).toProperty("id")
            .map(sourceId).toProperty("sourceId")
            .map(sourceBookId).toProperty("sourceBookId")
            .map(taskStatus).toProperty("taskStatus")
            .map(excCount).toProperty("excCount")
            .map(createTime).toProperty("createTime")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(CrawlSingleTask record) {
        return MyBatis3Utils.insert(this::insert, record, crawlSingleTask, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(sourceId).toPropertyWhenPresent("sourceId", record::getSourceId)
            .map(sourceBookId).toPropertyWhenPresent("sourceBookId", record::getSourceBookId)
            .map(taskStatus).toPropertyWhenPresent("taskStatus", record::getTaskStatus)
            .map(excCount).toPropertyWhenPresent("excCount", record::getExcCount)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<CrawlSingleTask> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, crawlSingleTask, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<CrawlSingleTask> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, crawlSingleTask, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<CrawlSingleTask> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, crawlSingleTask, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<CrawlSingleTask> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, crawlSingleTask, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(CrawlSingleTask record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(sourceId).equalTo(record::getSourceId)
                .set(sourceBookId).equalTo(record::getSourceBookId)
                .set(taskStatus).equalTo(record::getTaskStatus)
                .set(excCount).equalTo(record::getExcCount)
                .set(createTime).equalTo(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(CrawlSingleTask record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(sourceId).equalToWhenPresent(record::getSourceId)
                .set(sourceBookId).equalToWhenPresent(record::getSourceBookId)
                .set(taskStatus).equalToWhenPresent(record::getTaskStatus)
                .set(excCount).equalToWhenPresent(record::getExcCount)
                .set(createTime).equalToWhenPresent(record::getCreateTime);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(CrawlSingleTask record) {
        return update(c ->
            c.set(sourceId).equalTo(record::getSourceId)
            .set(sourceBookId).equalTo(record::getSourceBookId)
            .set(taskStatus).equalTo(record::getTaskStatus)
            .set(excCount).equalTo(record::getExcCount)
            .set(createTime).equalTo(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(CrawlSingleTask record) {
        return update(c ->
            c.set(sourceId).equalToWhenPresent(record::getSourceId)
            .set(sourceBookId).equalToWhenPresent(record::getSourceBookId)
            .set(taskStatus).equalToWhenPresent(record::getTaskStatus)
            .set(excCount).equalToWhenPresent(record::getExcCount)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}
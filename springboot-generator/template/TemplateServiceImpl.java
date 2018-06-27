package ${servicePackage}.impl;

import ${daoPackage}.${model}Dao;
import ${modelPackage}.${model};
import ${servicePackage}.${model}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${model}ServiceImpl implements ${model}Service {

    @Autowired
    private ${model}Dao ${modelParam}Dao;

    @Override
    public boolean save(${model} record){
        return ${modelParam}Dao.insert${model}Selective(record) == 1 ? true : false;
    }

    @Override
    public boolean delete(Long id){
        return ${modelParam}Dao.delete${model}ByPrimaryKey(id) == 1 ? true : false;
    }

    @Override
    public boolean update(${model} record){
        return ${modelParam}Dao.update${model}ByPrimaryKeySelective(record) == 1 ? true : false;
    }

    @Override
    public ${model} findById(Long id){
        return ${modelParam}Dao.select${model}ByPrimaryKey(id);
    }
    
}

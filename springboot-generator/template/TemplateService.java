package ${servicePackage};

import ${modelPackage}.${model};

public interface ${model}Service {
	
    boolean save(${model} record);

    boolean deleteById(${idType} id);

    boolean updateById(${model} record);

    ${model} findById(${idType} id);
    
}

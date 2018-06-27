package ${servicePackage};

import ${modelPackage}.${model};

public interface ${model}Service {
	
    boolean save(${model} record);

    boolean delete(Long id);

    boolean update(${model} record);

    ${model} findById(Long id);
    
}

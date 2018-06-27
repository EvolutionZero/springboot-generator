package ${controllerPackage};


import ${modelPackage}.${model};
import ${servicePackage}.${model}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/${modelParam}")
public class ${model}Controller {

    @Autowired
    private ${model}Service ${modelParam}Service;

    @RequestMapping(value = "/save.do")
    @ResponseBody
    public ${model} save(${model} ${modelParam}){
    	${modelParam}Service.save(${modelParam});
        return ${modelParam};
    }

    @RequestMapping(value = "/update.do")
    @ResponseBody
    public String update(${model} ${modelParam}){
        return ${modelParam}Service.updateById(${modelParam}) ? "ok" : "fail";
    }

    @RequestMapping(value="/find.do")
    @ResponseBody
    public ${model} find(@RequestParam(value="${idName}", defaultValue = "0") ${idType} ${idName}){
        return ${modelParam}Service.findById(${idName});
    }

    @RequestMapping(value="/delete.do")
    @ResponseBody
    public String delete(@RequestParam(value="${idName}", defaultValue = "0") ${idType} ${idName}){
        return ${modelParam}Service.deleteById(${idName}) ? "ok" : "fail";
    }

}

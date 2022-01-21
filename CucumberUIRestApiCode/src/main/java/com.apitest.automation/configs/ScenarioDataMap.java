package configs;

import com.apitest.automation.exceptions.AutomationExceptionHandler;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ScenarioDataMap {
    private Map<String, Map<String,Object>> scenarios = new HashMap();
    private String currentScenario = null;

    public ScenarioDataMap (String scenarioMap, String currentScenario, AutomationExceptionHandler exceptionHandler,String... overridePath) {
        try {
            Yaml yaml = new Yaml();
            String path = null;
            if (overridePath.length > 0 && overridePath[0].equals("override")) {
                path = scenarioMap;
            } else {
                path = "scenario_maps/" + scenarioMap;
            }

            InputStream input = this.getClass().getClassLoader().getResourceAsStream(path);
            Map<String, Map<String, Map<String, Object>>> map = (Map) yaml.load(input);
            this.scenarios.put("default", (Map<String, Object>) ((Map)map.get("scenarios")).get("default"));
            this.scenarios.put("function", (Map<String, Object>) ((Map)map.get("scenarios")).get("function"));
            this.currentScenario = currentScenario;
            this.scenarios.put(currentScenario,  (Map<String, Object>)((Map) map.get("scenarios")).get(currentScenario));
            Map var9 = (Map) this.scenarios.get(currentScenario);
        } catch (Exception var10) {
            exceptionHandler.handleException(var10, "Exception while preparing scenario Data for " + currentScenario);
        }
    }

    public Object getScenarioValue(String scenario, String key){
        return this.scenarios.containsKey(scenario) && ((Map)this.scenarios.get(scenario)).containsKey(key) ? ((Map)this.scenarios.get(scenario)).get(key) : ((Map)this.scenarios.get("default")).get(key);
    }
    public Map<String, Map<String,Object>> getScenarios()
    {
        return this.scenarios;
    }
    public String getCurrentScenario(){
        return this.currentScenario;
    }

    public void setScenarios(Map<String, Map<String, Object>> scenarios) {
        this.scenarios = scenarios;
    }
    public void setCurrentScenario(final String currentScenario){
        this.currentScenario = currentScenario;
    }
    public boolean equals(final Object o){
        if(o==this){
            return true;
        }else if (!(o instanceof ScenarioDataMap)){
            return false;
        }else{
            ScenarioDataMap other = (ScenarioDataMap)o;
            if(!other.canEqual(this)){
                return false;
            }else{
                Object this$Scenarios = this.getScenarios();
                Object other$Scenarios = this.getScenarios();
                if (this$Scenarios ==null){
                    if(other$Scenarios ==null){
                        return false;
                    }
                }else if (!this$Scenarios.equals(other$Scenarios)){
                    return false;
                }
                return true;
            }
        }
    }

    protected  boolean canEqual(final Object other) {
        return other instanceof ScenarioDataMap;
    }

    public int hasCode(){
//        int PRIME = true;
        int result = 1;
        Object $scenarios = this.getScenarios();
        result  = result * 59+($scenarios == null ? 43 : $scenarios.hashCode());
        Object $currentScenario = this.getCurrentScenario();
        result = result * 59 +($currentScenario == null ? 43 : $currentScenario.hashCode());
        return result;
    }

    public String toString(){
        return "ScenarioDataMap(Scenario="+ this.getScenarios()+" ,currentScenario="+this.getCurrentScenario() +")";
    }
}

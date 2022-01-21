//import javax.xml.bind.JAXBException;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;
//
//public class JsonXMLUtil {
//
//    private static ConcurrentReaderHashMap<String, JJAXBToJSONXMLMarshallerUnmarshaller> instances = new ConcurrentReaderHashMap<>();
//
//    private JsonXMLUtil() {
//
//    }
//
//    public static Object jsontoPojo(String josn, Class<?> clazz, String pojoPkg) {
//        Object pojo = null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            pojo = mapper.readValue(josn,clazz);
////            pojo = getMasrshaller(pojoPkg).umarshallJSONTOPOJO(clazz,json);
//        } catch (Exception e) {
//            pojo = null;
//            e.printStackTrace();
//        }
//        return pojo;
//    }
//
//    private static JAXBToJSONXMLMarshallerUnmarshaller getMasrshaller(String genPojokg) {
//        JAXBToJSONXMLMarshallerUnmarshaller umarshaller = instances.get(genPojokg);
//        JAXBToJSONXMLMarshallerUnmarshaller temp = null;
//        try {
//            temp = new JAXBToJSONXMLMarshallerUnmarshall(genPojokg);
//            umarshaller = instances.putIfAbsent(genPojokg, temp);
//            if (umarshaller == null) {
//                umarshaller = temp;
//            }
//        } catch (JAXBException e) {
//            umarshaller = null;
//        }
//        return umarshaller;
//    }
//
//
//}

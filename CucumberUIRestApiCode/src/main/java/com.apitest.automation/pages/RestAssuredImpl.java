package com.apitest.automation.pages;

import com.apitest.automation.configs.CongurationUtil;
import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
//import io.cucumber.datatable.DataTable;


import io.restassured.RestAssured;
import org.testng.Assert;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import jdk.nashorn.internal.ir.RuntimeNode;

import org.apache.http.util.Asserts;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class RestAssuredImpl {
    Response response = null;
//    public CashwireGlClassification ffrequestdetails = null;
    public static String loanPayloadJaon = null;
    public HashMap<String, String> FundingFundedJsonAttrovutes;
    public Map<String, String> expectedData = new HashMap<String, String>();
    private HashMap<String, String> currentLoanData;
    public String loanPayloadJson;
    public PrepareTestData prepareTestData = new PrepareTestData();

    public static void main(String[] args) throws IOException {
        System.out.println(getEndPointuri("\\Test11"));
    }

    public Response postRequest(RequestSpecification requestSpecification, String endpointName) {
        RequestSpecification request = null;
        try {
            request = requestSpecification;
            getEndPointuri(endpointName);
//            response = request.post(getEndPointuri(endpointName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response getResponse(RequestSpecification requestSpecification, String endPointName) throws IOException {
        RequestSpecification request = null;
        try {
            response = request.get(getEndPointuri(endPointName));
            Asserts.notNull(response.getBody(), "Check getbodyo is not empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void callLoanEndPoint(boolean addflag) {
        try {
            String endpoint = "earlyfunding/loan/fundingreqeust/processFundingRequests";
            if (addflag) {
                postRequest(getLoanHeaders("LoanSubmission"), endpoint);
            } else {
                postRequest(getLoanHeaders("LoanUpdate"), endpoint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createMultipleLoanRequestFprMultipParty(String baseFile, String fileExtension, DataTable customtable) {
        expectedData = createTestDataMapwithMultipleColumns(baseFile, fileExtension, customtable);
        int currentLoanCount = currentLoanData.size();
        for (int i = currentLoanCount + 1; i <= 10; i++) {
            String ddfLnId = expectedData.get("loan" + i);
            if (ddfLnId != null) {
                expectedData.put(ddfLnId, (new Random()).nextInt(1000) + "00" + i);
                currentLoanData.put("loan" + i, expectedData.get(ddfLnId));
            } else {
                expectedData.put("ddfLnID" + i, (new Random()).nextInt(1000) + "00" + i);
            }
        }
//        this.loanPayloadJson = partyMasterService.tranform(trimLoanBasedOnInput(prepareTestData.preparePayloadRequest(baseFile, fileExtension, expectedData), customtable));
        validateObjectNotNull(loanPayloadJson,"Loan Pay load is empty");
    }

    private void validateObjectNotNull(Object obj, String msg){
//        CompareSummary summary = new CompareSummary(this.getClass().getSimpleName());

        if (loanPayloadJson ==null){
            Assert.assertTrue(false, "Loan Pay load is empty");
        }
    }

    public Map<String, String> createTestDataMapwithMultipleColumns(String baseFile, String fileExtension,
                                                                    DataTable customTable) {
        Map<String, String> testData = new HashMap<>();
        String cellVal = null;
        Properties defaultData = getProperties(baseFile);
        for (String key : defaultData.stringPropertyNames()) {
            testData.put(key, defaultData.getProperty(key));
        }
        if (customTable != null) {
            for (DataTableRow dataRow : customTable.getGherkinRows()) {
                List<String> cells = dataRow.getCells();
                for (int i = 0; i < cells.size(); i = i + 2) {
                    if (!cells.get(i + 1).equalsIgnoreCase("null")) {
                        cellVal = cells.get(i + 1);
                    }
                    testData.put(cells.get(i), cellVal);
                }
            }
        }
        return testData;
    }

    public Properties getProperties(String baseFile) {
        Properties proeprties = new Properties();
        try {
            File file = new File(System.getProperty("user.dir") + "file.properties");
            FileInputStream fileinputSTream = new FileInputStream(file);
            proeprties.load(fileinputSTream);
            fileinputSTream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proeprties;
    }


    public RequestSpecification getLoanHeaders(String evenType) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("messageId", getMSgCreationime());
        request.header("msgCreationTime: \n", getMSgCreationime());
        request.header("eventType", evenType);
        request.header("sourcesystem", "R");
        request.body(loanPayloadJaon);
        request.relaxedHTTPSValidation();
        return request;
    }


    /*
         Call pasrToCashWirePojo method to FEtch the attribute from JsonObject
    */

//    private CashwireGlClassification pasrseToCashWirePojo(String jsonPayload) {
//        Object obj = JsonXMLUtil.jsontoPojo(jsonPayload, CashWireGLClassfication.clas, CashiwreConstatnts.CASH_WIRE_POJO_PKG);
//        return (CashwireGlClassification) obj;
//    }

    public HashMap<String, String> readCashWireAttributes(String requestPayload) throws Exception {
//        this.ffrequestdetails = pasrseToCashWirePojo(requestPayload);
//        FundingFundedJsonAttrovutes = new HashMap<String, String>();
//        String cashWrId = ffrequestdetails.getCashWire().getWireInfo().getCAshWireIdentifier();
//        String cashWrClrSysId = ffrequestdetails.getCashwire().getWireInfo().getWireReqClearanceSysIdentifier().getValue();
//        FundingFundedJsonAttrovutes.put("cashWrId", cashWrId);
//        FundingFundedJsonAttrovutes.put("cashWrClrSysId", cashWrClrSysId);
        return FundingFundedJsonAttrovutes;
    }

    public static XMLGregorianCalendar dateToXMLGergoiaCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gcal = new GregorianCalendar();
        XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        return xgcal;

    }

    public static String getMSgCreationime() {
        SimpleDateFormat dateForamt = new SimpleDateFormat("yyyy-MM-dd'T'HHmm:ss.SSSXXX");
        return dateForamt.format(Calendar.getInstance().getTime());
    }

    public static String getEndPointuri(String endpointName) throws IOException {
        return CongurationUtil.getBaseURL() + endpointName;

    }


}

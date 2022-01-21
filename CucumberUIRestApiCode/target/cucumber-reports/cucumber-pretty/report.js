$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/AutomationTest.feature");
formatter.feature({
  "line": 2,
  "name": "This is to test the automation api",
  "description": "",
  "id": "this-is-to-test-the-automation-api",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@ApiAutomationTesting"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "#  Background:"
    }
  ],
  "line": 6,
  "name": "validate api testing",
  "description": "",
  "id": "this-is-to-test-the-automation-api;validate-api-testing",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@Automation1"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "url endpoint \"restapi\"",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "restapi",
      "offset": 14
    }
  ],
  "location": "ApiAutomationStepDefination.getHealthEndPoint(String)"
});
formatter.result({
  "duration": 1501691600,
  "status": "passed"
});
});
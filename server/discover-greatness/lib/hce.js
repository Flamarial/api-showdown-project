var oauth = require("./oauth.js");
var request = require('request')

function provision(access_token, cb) {
    var url = 'https://api.discover.com/nws/nwp/hce/v2/account/provision';
    request({
        url: url,
        body: '{ "requestHeader": { "requestId": "accountprovisionsamplerequest1" ,"sessionId": "accountprovisionsamplesession1" ,"programId": "1234567890" ,"userContext":{ "walletId": "accountProvisionsamplewallet1" "deviceId": "accountProvisionsampledevice1" ,"userId": "accountprovisionsampleuser1"} }, "accountProvisionRequest": { "eligibilityContext": "provisionCorrelationId": "accountProvisionsamplecorrelation1" ,"termsAndConditionsId": "accountProvisionsampletermsandcondition1" ,"termsAndConditionsAcceptedDate": "2017-11-05T13:15:30.001Z" ,}, "secureContext": { "encryptedContent": "ew0KImNpZCI6ICIxMjMiLA0KInNvdXJjZSI6ICJvbi1maWxlIiwNCiJjYXB0dXJlTWV0aG9kIjogIjEiDQp9" ,}, "deviceContext": { "countryDuringProvision": "US" ,"deviceBrand": "sampledevicebrand" ,"deviceIp": "123.123.123.123" ,"deviceManufacturer": "Samsung" ,"deviceModel": "sampledevicemodel" ,"deviceName": "my phone" ,"deviceOSType": "sampledeviceostype" ,"deviceOSVersion": "4.4.2" ,"deviceOSCountry": "US" ,"deviceTimezone": "GMT-08:0" ,"deviceTimezoneSettings": "true" ,"deviceType": "2" ,"deviceUserId": "myGadget1" ,"imei": "45" ,"latitude": "+35.4534233" ,"language": "en-US" ,"longitude": "+45.4312423" ,"nameMismatch": "true" ,"networkOperator": "samplenetworkoperator" ,"networkType": "samplenetworktype" ,"numberOfTokensDevice": "4" ,"phoneNumber": "555-123-1231" ,"serialNumber": "123412341234" ,"deviceBluetoothMAC": "00-16-68-2B-40-90" ,}, "userProvisionContext": { "emailAddress": "sampleemail@example.com" ,"emailAddressAge": "30" ,"emailAddressCountry": "US"	,"hashedEmailAddress": "dffdgdfr43fr4o4o4fo4fo4" ,}, "riskContext": { "accountRisk": "4" ,"deviceRisk": "5" ,"provisioningRisk": "GREEN" ,"riskReason": [{ "reasonCode": "101" ,}, { "reasonCode": "102" ,}], "ageOfwalletAccount": "20" ,"fpanTenure": "10" ,"ageOfTokenizedCard": "7" ,"ageOfLastAccountChange": "1" ,"ageOfLastAccountActivity": "1" ,"totalTransactionCountForYear": "300" ,"ageOfDeviceUsageByAccount": "0" ,"totalProvisioningAttempts": "1" ,"suspendedTokensInAccount": "0" ,"numberOfTokensAccount": "9" ,}} } ',
        headers: { 'x-dfs-c-app-cert':'dfsexxoJA4kCvNElu_UAikI_-uY3nbQTNo_SL0t1I_LaXfOao', 'Accept':'application/json', 'Content-Type':'application/json', 'Cache-Control':'no-store', 'x-dfs-api-plan':'NWS-HCE-Sandbox', 'Authorization':'Bearer 524710db-c9ed-4c2f-b5ef-058d9ad70ee7', 'Content-Type':'application/json'  },
        method: 'POST'
    }, function (error, response, body) {
        if (error) {
            cb(error);
            console.log("error");
        } 
        console.log('Status', response.statusCode);
        // console.log('Headers', JSON.stringify(response.headers));
        // console.log('Reponse received', body);
        cb(null, body);
    });
}

function provisionRequestToServer(cb) {
    oauth.authenticatedRequest(provision, cb);
}

module.exports = {provisionRequestToServer: provisionRequestToServer};


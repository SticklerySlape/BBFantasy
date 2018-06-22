package bbfantasy;
// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

// [START sheets_quickstart]
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class SheetReadWrite {
    private String APPLICATION_NAME = "Big Brother Fantasy";
    private JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.
    private NetHttpTransport HTTP_TRANSPORT;
    private String spreadsheetId = ""; //your spreadsheet id here
    private Sheets service;
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private String CLIENT_SECRET_DIR = "client_secret.json";
    
    public SheetReadWrite() throws IOException, GeneralSecurityException {
    	HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If there is no client_secret.
     */
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
    	InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    /**
     * Returns the value at the cell
     */
    public String read(String range) throws IOException{
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            return "Error";
        } else {
            return (String)values.get(0).get(0);
        }
    }
    
    public List<List<Object>> readColumn(String range) throws IOException{
    	Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        return response.getValues();
    }
    
    public void write(String range) throws IOException {
    	ValueRange addOne = new ValueRange()
    		.setValues(Arrays.asList(Arrays.asList((Object)Integer.toString(Integer.parseInt(read(range)) + 1))));
    	service.spreadsheets().values()
    		.update(spreadsheetId, range, addOne)
    		.setValueInputOption("USER_ENTERED")
    		.execute();
    }
    
    public void append(String append) throws IOException {
    	ValueRange toAppend = new ValueRange()
    		.setValues(Arrays.asList(Arrays.asList((Object)append)));
    	service.spreadsheets().values()
    		.append(spreadsheetId, "Log!A1", toAppend)
    		.setValueInputOption("USER_ENTERED")
    		.setInsertDataOption("INSERT_ROWS")
    		.setIncludeValuesInResponse(true)
    		.execute();
    }
}
// [END sheets_quickstart]
#!/bin/bash

instanceFile=${WORKSPACE}/src/test/resources/application.properties
if [ -f "$instanceFile" ]; then
   environment_url=$(cat "$instanceFile" | grep environment.url)
   application_url=$(cat "$instanceFile" | grep application.url)
   es_path=$(cat "$instanceFile" | grep es.path)
   test_planId=$(cat "$instanceFile" | grep test.planId)
   test_planName=$(cat "$instanceFile" | grep test.planName)

   
   updated_environment_url="environment.url=$environmenturl"
   updated_application_url="application.url=$applicationurl"
   updated_es_path="es.path=$espath"
   updated_test_planId="test.planId=$testplanId"
   updated_test_planName="test.planName=$testplanName"
    
   # SED command to replace exisitng line with newly updated line in InstanceFile
   sed -i "s|$environment_url|$updated_environment_url|g" "$instanceFile"
   sed -i "s|$application_url|$updated_application_url|g" "$instanceFile"
   sed -i "s|$es_path|$updated_es_path|g" "$instanceFile"
   sed -i "s/$test_planId/$updated_test_planId/g" "$instanceFile"
   sed -i "s/$test_planName/$updated_test_planName/g" "$instanceFile"
fi

--
-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements.  See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership.  The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License.  You may obtain a copy of the License at
--
--  http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an
-- "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
-- KIND, either express or implied.  See the License for the
-- specific language governing permissions and limitations
-- under the License.
--
create database ycsb;
use ycsb;

# not necessary to create user if we grant (and not supported in some dialects)
# create user 'brooklyn' identified by 'br00k11n';

grant usage on *.* to 'brooklyn'@'%' identified by 'br00k11n';

# ''@localhost is sometimes set up, overriding brooklyn@'%', so do a second explicit grant
grant usage on *.* to 'brooklyn'@'localhost' identified by 'br00k11n';

grant all privileges on ycsb.* to 'brooklyn'@'%';

flush privileges;

-- Creates a Table.

-- Drop the table if it exists;
DROP TABLE IF EXISTS usertable;

-- Create the user table with 5 fields.
CREATE TABLE usertable(YCSB_KEY VARCHAR (255) PRIMARY KEY,
  FIELD1 TEXT, FIELD2 TEXT,
  FIELD3 TEXT, FIELD4 TEXT,
  FIELD5 TEXT, FIELD6 TEXT,
  FIELD7 TEXT, FIELD8 TEXT,
  FIELD9 TEXT, FIELD10 TEXT);

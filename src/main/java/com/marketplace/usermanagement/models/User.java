package com.marketplace.usermanagement.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "marketplace-user")
public class User {
    @DynamoDBHashKey
    String userId;
    @DynamoDBAttribute
    String firstName;
    @DynamoDBAttribute
    String lastName;
    @DynamoDBAttribute
    String email;
    @DynamoDBAttribute
    String phoneNumber;
    @DynamoDBAttribute
    String role;
    @DynamoDBAttribute
    Date createdAt;
    @DynamoDBAttribute
    Date updatedAt;
}

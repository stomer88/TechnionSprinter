package com.example.izzy.sprinter.Utilities;

import java.util.UUID;

/**
 * Created by izzy on 23/05/15.
 */
public class GenerateUniqueID {
    public static UUID getUniqueID()
    {
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey;
    }
}

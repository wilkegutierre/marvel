package com.example.marvel.utils

import java.math.BigInteger
import java.security.MessageDigest

class MarvelHashCode {

    val public_key: String  = "6d923a3ce3e227199e17c1ea42401f0a";
    val private_key: String = "6f2949000f6bbef34be364e0ed49795714ea88f4";

    fun marvelHash(timestamp: Long) : String {
        val md5: MessageDigest = MessageDigest.getInstance("MD5")
        val hashKey: String = timestamp.toString() + private_key + public_key
        val hashByte: ByteArray = md5.digest(hashKey.toByteArray())
        md5.update(hashKey.toByteArray(), 0, hashKey.length)
        val hashCode: String = md5.digest().toString()
        val bigInter = BigInteger(1, hashByte)
        val hashReturn: String = bigInter.toString(16)

        return hashReturn
    }
}
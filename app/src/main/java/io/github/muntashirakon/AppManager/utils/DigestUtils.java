/*
 * Copyright (C) 2020 Muntashir Al-Islam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.muntashirakon.AppManager.utils;

import android.annotation.TargetApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

public class DigestUtils {
    @StringDef({MD2, MD5, SHA_1, SHA_224, SHA_256, SHA_384, SHA_512})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Algorithm {
    }

    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    @TargetApi(22)
    public static final String SHA_224 = "SHA-224";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";

    @NonNull
    public static String getHexDigest(@Algorithm String algo, @NonNull byte[] bytes) {
        return Utils.bytesToHex(getDigest(algo, bytes));
    }

    @NonNull
    public static byte[] getDigest(@Algorithm String algo, @NonNull byte[] bytes) {
        try {
            return MessageDigest.getInstance(algo).digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @NonNull
    public static MessageDigest getMD(@Algorithm String algo) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algo);
    }
}

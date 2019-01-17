package com.github.aaabramov.crypto;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

// Run complete.
//
// Benchmark                       Mode  Cnt     Score     Error  Units
// CryptoInit.withAvailableCipher  avgt   30  1795.563 ±  37.173  ns/op
// CryptoInit.withNewCipher        avgt   30  6979.507 ± 144.121  ns/op
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CryptoInit {
    
    private static byte[] MESSAGE_TO_ENCRYPT = "catch me if you can".getBytes(UTF_8);
    
    private static class KeyContainer {
        static final Key key;
        static final SecureRandom SECURE_RANDOM = new SecureRandom();
        
        static {
            try {
                KeyGenerator generator = KeyGenerator.getInstance("AES");
                generator.init(256);
                
                key = new SecretKeySpec(generator.generateKey().getEncoded(), "AES");
            } catch (Exception e) {
                // ignored
                throw new RuntimeException(e);
            }
        }
        
        static IvParameterSpec generateIV() {
            byte[] bytes = new byte[16];
            SECURE_RANDOM.nextBytes(bytes);
            return new IvParameterSpec(bytes);
        }
        
    }
    
    @State(Scope.Benchmark)
    public static class CipherContainer {
        
        private ThreadLocal<Cipher> cipherThreadLocal = ThreadLocal.withInitial(() -> {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5Padding");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        
        Cipher getCipher() throws Exception {
            Cipher cipher = cipherThreadLocal.get();
            cipher.init(Cipher.ENCRYPT_MODE, KeyContainer.key, KeyContainer.generateIV());
            return cipher;
        }
    }
    
    @Benchmark
    public void withAvailableCipher(CipherContainer container, Blackhole bh) throws Exception {
        bh.consume(container.getCipher().doFinal(MESSAGE_TO_ENCRYPT));
    }
    
    @Benchmark
    public void withNewCipher(Blackhole bh) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, KeyContainer.key, KeyContainer.generateIV());
        bh.consume(cipher.doFinal(MESSAGE_TO_ENCRYPT));
    }
    
}

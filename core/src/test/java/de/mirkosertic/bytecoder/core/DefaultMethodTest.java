/*
 * Copyright 2018 Mirko Sertic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mirkosertic.bytecoder.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.mirkosertic.bytecoder.unittest.BytecoderUnitTestRunner;

@RunWith(BytecoderUnitTestRunner.class)
public class DefaultMethodTest {

    public interface WithDefault {

        default int add(final int a, final int b) {
            return a + b;
        }
    }

    public class Impl implements WithDefault {

        private int compute(final int a, final int b) {
            return add(a, b);
        }
    }

    @Test
    public void testDefaultImplInvocation() {
        final Impl theImpl = new Impl();
        final int theResult = theImpl.compute(10, 20);
        Assert.assertEquals(30, theResult, 0);
    }
}
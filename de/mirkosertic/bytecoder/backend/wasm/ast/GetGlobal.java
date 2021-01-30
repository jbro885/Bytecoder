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
package de.mirkosertic.bytecoder.backend.wasm.ast;

import de.mirkosertic.bytecoder.ssa.Expression;

import java.io.IOException;

public class GetGlobal implements WASMExpression {

    private final Global global;
    private final Expression expression;

    GetGlobal(final Global global, final Expression expression) {
        this.global = global;
        this.expression = expression;
    }

    @Override
    public void writeTo(final TextWriter textWriter, final ExportContext context) {
        textWriter.opening();
        textWriter.write("get_global");
        textWriter.space();
        textWriter.writeLabel(global.getLabel());
        textWriter.closing();
    }

    @Override
    public void writeTo(final BinaryWriter.Writer codeWriter, final ExportContext context) throws IOException {
        codeWriter.registerDebugInformationFor(expression);
        codeWriter.writeByte((byte) 0x23);
        codeWriter.writeUnsignedLeb128(context.globalsIndex().indexOf(global));
    }
}

/*
 * Copyright (c) 2017. Real Time Genomics Limited.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the
 *    distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rtg.simulation.reads;

import com.rtg.util.io.MemoryPrintStream;

import junit.framework.TestCase;

/**
 * Test class
 */
public class FastqReadWriterTest extends TestCase {

  public void testSingle() throws Exception {
    final MemoryPrintStream out = new MemoryPrintStream();
    final FastqReadWriter f = new FastqReadWriter(out.lineWriter(), false);
    f.writeRead("foo", new byte[] {0, 1, 2, 3, 4}, new byte[] {20, 20, 20, 20, 20}, 5);
    assertEquals("@0 foo\nNACGT\n+\n55555\n", out.toString());
    assertEquals(1, f.readsWritten());
  }

  public void testPaired() throws Exception {
    final MemoryPrintStream out = new MemoryPrintStream();
    final FastqReadWriter f = new FastqReadWriter(out.lineWriter(), true);
    f.writeLeftRead("foo", new byte[] {0, 1, 2, 3, 4}, new byte[] {20, 20, 20, 20, 20}, 5);
    f.writeRightRead("foo", new byte[] {0, 1, 2, 3, 4}, new byte[] {20, 20, 20, 20, 20}, 5);
    assertEquals("@0 foo 1\nNACGT\n+\n55555\n@0 foo 2\nNACGT\n+\n55555\n", out.toString());
    assertEquals(1, f.readsWritten());
  }

}

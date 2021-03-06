/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.armeria.server.file;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.linecorp.armeria.common.MediaType;

public class MimeTypeUtilTest {

    @Test
    public void knownExtensions() {
        assertThat(MediaType.PNG.is(MimeTypeUtil.guessFromPath("image.png", false))).isTrue();
        assertThat(MediaType.PNG.is(MimeTypeUtil.guessFromPath("/static/image.png", false))).isTrue();
        assertThat(MediaType.PDF.is(MimeTypeUtil.guessFromPath("document.pdf", false))).isTrue();
    }

    @Test
    public void preCompressed() {
        assertThat(MediaType.PNG.is(MimeTypeUtil.guessFromPath("image.png.gz", true))).isTrue();
        assertThat(MediaType.PNG.is(MimeTypeUtil.guessFromPath("/static/image.png.br", true))).isTrue();
        assertThat(MediaType.OCTET_STREAM.is(MimeTypeUtil.guessFromPath("image.png.gz", false))).isTrue();
    }

    @Test
    public void guessed() {
        assertThat(MediaType.ZIP.is(MimeTypeUtil.guessFromPath("bundle.zip", false))).isTrue();
    }

    @Test
    public void unknown() {
        assertThat(MimeTypeUtil.guessFromPath("unknown.extension", false)).isNull();
    }
}

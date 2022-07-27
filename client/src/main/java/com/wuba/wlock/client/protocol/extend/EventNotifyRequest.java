/*
 * Copyright (C) 2005-present, 58.com.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wuba.wlock.client.protocol.extend;

import com.wuba.wlock.client.exception.ProtocolException;
import com.wuba.wlock.client.helper.ByteConverter;
import com.wuba.wlock.client.protocol.WLockRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EventNotifyRequest extends WLockRequest {
	public static int EXTEND_FIXED_LENGTH = 20;
	/** 锁版本*/
	protected long fencingToken;
	
	private long watchID;
	
	private int eventType;

	public long getFencingToken() {
		return fencingToken;
	}

	public void setFencingToken(long fencingToken) {
		this.fencingToken = fencingToken;
	}

	public long getWatchID() {
		return watchID;
	}

	public void setWatchID(long watchID) {
		this.watchID = watchID;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public byte[] genExtraBytes() throws ProtocolException {
		ByteArrayOutputStream stream = null;
		try {
			stream = new ByteArrayOutputStream();
			
			stream.write(ByteConverter.longToBytesLittleEndian(this.fencingToken));
			stream.write(ByteConverter.longToBytesLittleEndian(this.watchID));
			stream.write(ByteConverter.intToBytesLittleEndian(this.eventType));

			return stream.toByteArray();
		} catch (Exception e) {
			throw new ProtocolException(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new ProtocolException(e);
				}
			}
		}
	}

	@Override
	public void parseExtraBytes(byte[] buf, int index) throws ProtocolException {
		long fencingToken = ByteConverter.bytesToLongLittleEndian(buf, index);
		index += 8;
		this.setFencingToken(fencingToken);
		
		long watchid = ByteConverter.bytesToLongLittleEndian(buf, index);
		this.setWatchID(watchid);
		index += 8;
		
		int eventType = ByteConverter.bytesToIntLittleEndian(buf, index);
		this.setEventType(eventType);
		index += 4;
	}

	@Override
	public boolean isAsync() {
		return false;
	}

}
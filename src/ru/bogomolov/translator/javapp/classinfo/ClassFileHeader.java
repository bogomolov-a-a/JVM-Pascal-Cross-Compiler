package ru.bogomolov.translator.javapp.classinfo;

import java.io.DataInputStream;
import java.io.IOException;

class ClassFileHeader
{
	/* Class File Constants */
    private static final int JVM_MAGIC                   = 0xcafebabe;
	 /*1.0.2 version*/
    private static final int MINIMUM_JVM_VERSION                 = 52;    
    private final short             minor_version;
    private final short             major_version;
	ClassFileHeader(DataInputStream dis)throws IOException
	{
		int magic=dis.readInt();
		validateClassMarker(magic);
		minor_version=dis.readShort();
		major_version=dis.readShort();
		System.out.println("Class file version is "+getJVMVersion());
		if(major_version<MINIMUM_JVM_VERSION)
		{
			throw new IOException("Class file supported starts with JVM 8! Early version is deprecated.");
		}		
	}
	private void validateClassMarker(int magic)throws IOException
	{
		if(magic!=JVM_MAGIC)
		{
			throw new IOException("wrong magic!");
		}
	}
	public String getJVMVersion()
	{
		return major_version+"."+minor_version;
	}
}
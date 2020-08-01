package ru.bogomolov.translator.javapp.classinfo.constantpool;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ConstantPool
{	
	private final List<ConstantPoolEntry> entries;
	public ConstantPool(DataInputStream dis)throws IOException
	{
		short size=dis.readShort();
		size--;
		if(size<=0)
		{
			throw new IOException("wrong constant pool size! Must be greather than 1");
		}
		System.out.println("Constant Pool entry count is "+realSize);
		entries=new ArrayList<>();
		for(short i=0;i<realSize;i++)
		{
			System.out.println("reading constant pool index "+i);
			entries.add(new ConstantPoolEntry(dis));
			System.out.println("constant pool index "+i+ " read.");
		}
	}
	public ConstantPoolEntry getEntryValue(int index)
	{		
		return entries.get(index);		
	}	
}
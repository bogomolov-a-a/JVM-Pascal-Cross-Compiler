package ru.bogomolov.translator.javapp;

import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import ru.bogomolov.translator.javapp.classinfo.ClassFile;

public class Main
{
	public static void main(String[] args)throws Exception
	{
		try(JarFile jarFile=new JarFile(args[0],true);)
		{
			JarEntry jarEntry=jarFile.getJarEntry(args[1].replaceAll("\\.","/")+".class");
			try(InputStream stream=jarFile.getInputStream(jarEntry);)
			{
				new ClassFile(stream);
				
			}
		}
	}
}
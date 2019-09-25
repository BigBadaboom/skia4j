# Build a version of the site with compressed javascript and css files

import sys, os, re, shutil, glob, tempfile 

#********************************************************************************************************
# Run this script from a Visual Studio command prompt
# Start -> All Programs -> Visual Studio 2017 -> Visual Studio Tools -> Developer Command Prompt for VS
# May also need to set env vars:
# C:\Program Files (x86)\Microsoft Visual Studio\2019\Community>  VC\Auxiliary\Build\vcvars64.bat
# Then:
# >  cd d:Users\Paul\DevelopmentWorkspaces\EclipseWorkspace\skia4j
# > python build-dll.py
#********************************************************************************************************

CC_LINKER = "cl"
#CC_LINKER_FLAGS = "/MD /LD /NODEFAULTLIB:libcmt.lib /NODEFAULTLIB:libcmtd.lib /NODEFAULTLIB:msvcrtd.lib"    # Link with msvcrt.lib for multithreading. Create DLL; 
CC_LINKER_FLAGS = "/LD /DLL" 

JAVA_H_DEST_DIR = os.path.join("native","src","include")
JNI_H_FILE_DIR = os.path.join(os.environ['JAVA_HOME'], "include")
JNI_H_FILE_ARCH_DIR = os.path.join(os.environ['JAVA_HOME'], "include", "win32")

#SKIA_HEADERS_CONFIG_DIR = os.path.join("external", "skia","include","config")
#SKIA_HEADERS_CORE_DIR = os.path.join("external", "skia","include","core")
SKIA_HEADERS_CONFIG_DIR = os.path.join("external", "skia")

WIN64_SKIA_LIB = os.path.join("native","win64","skia-win-x64.lib")
WIN64_SKIA4J_OUTPUT_FILE = os.path.join("native","out", "skia4j-win-x64.dll")

# Required by Windows builds of Skia
WINDOWS_LIBS = "user32.lib" 

# The Skia4J native JNI interface code
C_SOURCE_DIR = os.path.join("native","src")

INCLUDES = [JAVA_H_DEST_DIR,
            JNI_H_FILE_DIR,
            JNI_H_FILE_ARCH_DIR,
            SKIA_HEADERS_CONFIG_DIR]
#            SKIA_HEADERS_CORE_DIR]


def compileDLL():
  """Compile the API Java source"""

  sys.stdout.write('\nCompiling and linking the library...\n')

  srcFiles = findCSourceFiles(C_SOURCE_DIR)
  if len(srcFiles) == 0:
    sys.stdout.write('No C/C++ source files found.\n')
    return False

  includes = "/I\"" + "\" /I\"".join(INCLUDES) + "\""

  #cl -Ic:\java\include -Ic:\java\include\win32 -MD -LD HelloWorld.c -FeHelloWorld.dll
  #cmd = "%s /I%s /I%s /I%s %s %s /Fe%s" % (CC_LINKER, CC_STD_HEADER_DIR, JAVA_H_DEST_DIR, WIN64_SKIA_LIB_DIR, CC_LINKER_FLAGS, " ".join(srcFiles), WIN64_SKIA4J_LIB_FILE)
  cmd = "%s %s %s %s %s %s /Fe%s /link" % (CC_LINKER, includes, CC_LINKER_FLAGS, " ".join(srcFiles), WIN64_SKIA_LIB, WINDOWS_LIBS, WIN64_SKIA4J_OUTPUT_FILE)
  sys.stdout.write(cmd + "\n")

  res = os.system(cmd)
  return res == 0



def findCSourceFiles(rootDir):
  """Recurse through a directory tree and find all the .c and .cpp files"""

  result = []
  for dirpath, dirnames, filenames in os.walk(rootDir):
    for name in filenames:
      className, fileExtension = os.path.splitext(name)
      if (fileExtension == ".c" or fileExtension == ".cpp"):
        result.append(os.path.join(dirpath, name))

  return result




def main():

  if not compileDLL():
    return;



if __name__ == "__main__":
  main()

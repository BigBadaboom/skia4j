# Build a version of the site with compressed javascript and css files

import sys, os, re, shutil, glob, tempfile 


SKIA_API_SRC_DIR = os.path.join("src","main","java")
JAVA_H_DEST_DIR = os.path.join("native","src","include")
#JNI_H_FILE = os.path.join(os.environ['JAVA_HOME'], "include", "jni.h")

JAVAC_CMD = "javac"
JAVAC_CLASSPATH = "."
JDK_RELEASE_VERSION = "8"
JAVAH_CMD = "javah"
COMPILER = "clang"



def compileJavaAPI(classesBuildDir):
  """Compile the API Java source"""

  sys.stdout.write('\nCompiling java source...\n')

  srcFiles = findJavaFiles(SKIA_API_SRC_DIR)
  if len(srcFiles) == 0:
    sys.stdout.write('No java source files found.\n')
    return False

  cmd = "%s -d %s -cp %s -source %s -target %s %s" % (JAVAC_CMD, classesBuildDir, JAVAC_CLASSPATH, JDK_RELEASE_VERSION, JDK_RELEASE_VERSION, " ".join(srcFiles))
  sys.stdout.write(cmd + "\n")

  res = os.system(cmd)
  return res == 0



def findJavaFiles(rootDir):
  """Recurse through a directory tree and find all the .java files"""

  result = []
  for dirpath, dirnames, filenames in os.walk(rootDir):
    for name in filenames:
      className, fileExtension = os.path.splitext(name)
      if (fileExtension == ".java"):
        result.append(os.path.join(dirpath, name))

  return result



def generateJavaH(classesBuildDir):
  """Generate the JNI .h file from the Java classes""" 

  sys.stdout.write('\nCalling javah to generate JNI .h files...\n')

  classes = findClassFiles(classesBuildDir)
  if len(classes) == 0:
    sys.stdout.write('No class files found.\n')
    return;

  cmd = "%s -classpath %s -d %s -v %s" % (JAVAH_CMD, classesBuildDir, JAVA_H_DEST_DIR, " ".join(classes))
  sys.stdout.write(cmd + "\n")
  #os.system("dir "+classesBuildDir)
  os.system(cmd)

  res = os.system("dir "+JAVA_H_DEST_DIR)
  return res == 0



def findClassFiles(rootDir):
  """Recurse through a directory tree and find all the .java files"""

  cwd = os.getcwd()
  os.chdir(rootDir)

  result = []
  for dirpath, dirnames, filenames in os.walk("."):
    for name in filenames:
      className, fileExtension = os.path.splitext(name)
      if (fileExtension == ".class"):
        # Found a class. Derive the fully qualified class name.
        parts = os.path.normpath(dirpath)
        parts = parts.split(os.path.sep)
        result.append(".".join(parts) + "." + className)

  os.chdir(cwd)
  return result



#def copyJniDotHIncludeFile():
#  """Copy the jni.h file from the SDK to the dest include dir""" 
#
#  sys.stdout.write('\nCopying jni.h file to dir containing generated JNI .h files...\n')
#  shutil.copy(JNI_H_FILE, JAVA_H_DEST_DIR)



def main():

  buildDir = tempfile.mkdtemp()
  
  if not compileJavaAPI(buildDir):
    return;

  if not generateJavaH(buildDir):
    return;

  #copyJniDotHIncludeFile()


  # Remove temp dir
  #shutil.rmtree(buildDir)
  sys.stdout.write("\n classesDir = " + buildDir + "\n")




if __name__ == "__main__":
  main()

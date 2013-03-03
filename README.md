I. Introduction

This project contains the complete OASIS SCA 1.1 Web Services Binding Specification Conformance Tests adapted to the Fabric3 runtime. The Conformance Tests
are taken from revision 311 of the OASIS repository located at https://tools.oasis-open.org/version-control/svn/sca-bindings/TestCases_BWS/.

II. Running the Tests

To run the tests, do the following:

1. Checkout master from https://github.com/Fabric3/fabric3-core and build the source.

2. Checkout the adapted Conformance Tests and build the source from the root directory using: mvn clean install. You will need to increase the JVM heap
settings from the default value if you experience an out of memory error. The build has been verified with Maven 3.0.4.


Issues should be reported to the Fabric3 user list: http://xircles.codehaus.org/projects/fabric3/lists.
SUMMARY = "HTTP Echo Server - Returns request body as response"
DESCRIPTION = "Minimal Python HTTP service that echoes request body, suitable for container deployment"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# For local development - embedded webservice
SRC_URI = "file://webservice"

S = "${UNPACKDIR}/webservice"

inherit setuptools3

RDEPENDS:${PN} = "python3-core python3-modules"

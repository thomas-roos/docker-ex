SUMMARY = "Python Echo Webservice container image"
DESCRIPTION = "Minimal container image with Python HTTP echo service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

OCI_IMAGE_ENTRYPOINT = "/usr/bin/echo-server"
OCI_IMAGE_ENTRYPOINT_ARGS = "--host 0.0.0.0 --port 8080"
CONTAINER_SHELL = "busybox"

IMAGE_INSTALL:append = " webservice-python"

IMAGE_FSTYPES = "container oci"
inherit image
inherit image-oci

IMAGE_FEATURES = ""
IMAGE_LINGUAS = ""
NO_RECOMMENDATIONS = "1"

IMAGE_INSTALL = " \
       base-files \
       base-passwd \
       netbase \
       python3-core \
       python3-modules \
       ${CONTAINER_SHELL} \
"

# Container shell configuration
CONTAINER_SHELL ?= "${@bb.utils.contains('PACKAGE_EXTRA_ARCHS', 'container-dummy-provides', 'container-dummy-provides', 'busybox', d)}"

# Allow build with or without a specific kernel
IMAGE_CONTAINER_NO_DUMMY = "1"

# Workaround /var/volatile for now
ROOTFS_POSTPROCESS_COMMAND += "rootfs_fixup_var_volatile ; "
rootfs_fixup_var_volatile () {
    install -m 1777 -d ${IMAGE_ROOTFS}/${localstatedir}/volatile/tmp
    install -m 755 -d ${IMAGE_ROOTFS}/${localstatedir}/volatile/log
}

# Expose port 8080
OCI_IMAGE_PORTS = "8080"

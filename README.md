A yocto layer setup to build a minimal webservice docker container.


## Building with bitbake-setup

1. Clone with submodules:

```bash
git clone --recurse-submodules https://github.com/thomas-roos/docker-ex
```

Or if already cloned:

```bash
git submodule update --init --recursive
```

2. Initialize the build environment:

**For ARM64:**
```bash
cd bitbake/bin/ && \
./bitbake-setup --setting default top-dir-prefix $PWD/../../ init \
  $PWD/../../bitbake-setup.conf.json \
  docker-ex machine/qemuarm64 distro/poky application/docker-ex core/yocto/sstate-mirror-cdn --non-interactive && \
  cd -
```

**For x86-64:**
```bash
cd bitbake/bin/ && \
./bitbake-setup --setting default top-dir-prefix $PWD/../../ init \
  $PWD/../../bitbake-setup.conf.json \
  docker-ex machine/qemux86-64 distro/poky application/docker-ex core/yocto/sstate-mirror-cdn --non-interactive && \
  cd -
```

3. Source the build environment:

**For ARM64:**
```bash
. ./bitbake-builds/bitbake-setup-docker-ex-machine_qemuarm64-distro_poky/build/init-build-env
```

**For x86-64:**
```bash
. ./bitbake-builds/bitbake-setup-docker-ex-machine_qemux86-64-distro_poky/build/init-build-env
```

4. Build the image:

```bash
bitbake webservice-container
```

5. Import the image (one-time setup):

**For ARM64:**
```bash
docker import --change 'ENTRYPOINT ["/usr/bin/echo-server"]' --change 'CMD ["--host", "0.0.0.0", "--port", "8080"]' --change 'EXPOSE 8080' ./tmp/deploy/images/qemuarm64/webservice-container-qemuarm64.rootfs.tar.bz2 webservice-container:latest
```

**For x86-64:**
```bash
docker import --change 'ENTRYPOINT ["/usr/bin/echo-server"]' --change 'CMD ["--host", "0.0.0.0", "--port", "8080"]' --change 'EXPOSE 8080' ./tmp/deploy/images/qemux86-64/webservice-container-qemux86-64.rootfs.tar.bz2 webservice-container:latest
```

6. Run and test the container

```bash
docker run -p 8080:8080 webservice-container:latest
```

In a different termninal you can test it

```bash
curl -X POST -d "Hello Docker!" http://localhost:8080/test
```
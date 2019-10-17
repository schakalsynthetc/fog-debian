TARGETS = hostname.sh mountkernfs.sh udev hwclock.sh mountdevsubfs.sh checkroot.sh urandom mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh bootmisc.sh udev-finish checkfs.sh checkroot-bootclean.sh procps x11-common
INTERACTIVE = udev checkroot.sh checkfs.sh
udev: mountkernfs.sh
hwclock.sh: mountdevsubfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
bootmisc.sh: mountnfs-bootclean.sh mountall.sh mountall-bootclean.sh mountnfs.sh udev checkroot-bootclean.sh
udev-finish: udev mountall.sh mountall-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
x11-common: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh

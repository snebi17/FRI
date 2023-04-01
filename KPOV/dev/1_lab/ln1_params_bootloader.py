#!/usr/bin/env python3

import random
import socket
import struct
import sys

def MAC_gen(r):
    s = "0123456789abcdef"
    return ":".join([r.choice(s) + r.choice("26ae")] + \
        [r.choice(s) + r.choice(s) for i in range(5)])

def _net_to_int(s):
    try:
        net, subnet = s.split('/')
    except ValueError:
        subnet = '32'
    try:
        subnet_int = int(subnet)
    except ValueError:
        subnet_bytes = struct.unpack('>I', socket.inet_aton(subnet))[0]
        max_bit = 1 << 31
        subnet_int = 0
        while (subnet_bytes & max_bit) > 0:
            subnet_int += 1
            max_bit >>= 1
    return struct.unpack('>I', socket.inet_aton(net))[0], subnet_int
            
def IPv4_subnet_gen(r, base_net, mask = 24):
    base_addr, base_subnet = _net_to_int(base_net)
    a = r.randint(1, 1 << mask - base_subnet) << (32 - mask)
    if a >= 1<<32:
        a = 0
    net_addr = base_addr | a
    return socket.inet_ntoa(struct.pack('>I', net_addr)) + '/{0}'.format(mask)

def IPv4_addr_gen(r, network, n=1, reserve_top=1, reserve_bottom=1):
    net, mask = _net_to_int(network)
    hosts = []
    for i in r.sample(range(reserve_bottom, 2**(32-mask)-reserve_top), n):
        hosts.append(socket.inet_ntoa(struct.pack('>I', net | i)))
    return hosts

if __name__ == '__main__':
    if sys.version_info < (3, 2):
        print('Please run the script using Python 3.2 or above.')
        sys.exit(1)

    if len(sys.argv) < 2:
        print('Usage: {} <student ID>'.format(sys.argv[0]))
        sys.exit(2)

    vpisna = sys.argv[1]
    r = random.Random(vpisna)
    mac = MAC_gen(r)
    base = '172.16.0.0/12'
    mask = r.randint(25, 26)
    net = IPv4_subnet_gen(r, base, mask)
    low = IPv4_addr_gen(r, net, 1, reserve_bottom=10, reserve_top=int(0.7*2**(32-mask)))[0]
    high = IPv4_addr_gen(r, net, 1, reserve_bottom=int(0.7*2**(32-mask)))[0]
    print('Student ID: {}'.format(vpisna))
    print('MAC address: {}'.format(mac))
    print('Network: {}'.format(net))
    print('DHCP address range: {} - {}'.format(low, high))
